package com.plusplm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.JsonObject;
import com.plusplm.config.Globals;

@Component
public class SystemInfor {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static JsonObject ganttPagination(int listSize, int currPage, int countPerPage) {

		int totalPages = (listSize + (countPerPage - 1)) / countPerPage;
		boolean isFirst = currPage == 0 ? true : false;
		boolean isLast = currPage == totalPages - 1 ? true : false;

		JsonObject pageObj = new JsonObject();
		pageObj.addProperty("last", isLast);
		pageObj.addProperty("totalPages", totalPages);
		pageObj.addProperty("totalElements", listSize);
		pageObj.addProperty("number", currPage);
		pageObj.addProperty("size", countPerPage);
		pageObj.add("sort", null);
		pageObj.addProperty("numberOfElements", countPerPage);
		pageObj.addProperty("first", isFirst);

		return pageObj;
	}

	public static JsonObject shipDwgPagination(int listSize, int currPage, int countPerPage) {

		int totalPages = (listSize + (countPerPage - 1)) / countPerPage;
		boolean isFirst = currPage == 0 ? true : false;
		boolean isLast = currPage == totalPages - 1 ? true : false;

		JsonObject pageObj = new JsonObject();
		pageObj.addProperty("last", isLast);
		pageObj.addProperty("totalPages", totalPages);
		pageObj.addProperty("totalElements", listSize);
		pageObj.addProperty("number", currPage);
		pageObj.addProperty("size", countPerPage);
		pageObj.add("sort", null);
		pageObj.addProperty("numberOfElements", countPerPage);
		pageObj.addProperty("first", isFirst);

		return pageObj;
	}

	public static String SysDate() {
		long curr = System.currentTimeMillis();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = sdf2.format(new Date(curr));
		return sysDate;
	}

	public static String SysTime() {
		long curr = System.currentTimeMillis();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sysDate = sdf2.format(new Date(curr));
		return sysDate;
	}

	public static String SysSec() {
		long curr = System.currentTimeMillis();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sysDate = sdf2.format(new Date(curr));
		return sysDate;
	}

	public static String ClientIp() throws SocketException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String isNull(Object param) throws Exception {
		String str = "";

		if (param == null || param == "undefined" || param == "null") {
			return "";
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date) param).toString();
		} else {
			str = String.valueOf(param);
		}

		if (str.equals("")) {
			return "";
		} else {
			return str.trim();
		}
	}

	public static long isNumNull(Object param) throws Exception {
		String str = "";

		if (param == null) {
			return 0;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}

		if (str.equals("")) {
			return 0;
		} else {
			return Long.parseLong(str);
		}
	}

	public static int isIntNull(Object param) throws Exception {
		String str = "";

		if (param == null) {
			return 0;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}

		if (str.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}

	public static String isDateNull(Object param) throws Exception {
		String str = "";

		if (param == null || param == "undefined" || param == "null" || param == "" || "".equals(param)) {
			str = SysSec();
		} else {
			str = param.toString();
		}
		return str;
	}

	public static String isSortOrder(Object param) throws Exception {
		String str = "";

		if (param == null || param == "undefined" || param == "null" || param == "" || "".equals(param)) {
			return "";
		} else {

		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date) param).toString();
		} else {
			str = String.valueOf(param);
		}

		if (str.equals("")) {
			return "";
		} else {
			return str.trim().toUpperCase();
		}
	}

	public static String Base64encode(String val) throws Exception {
		String rtnVal = val;
		byte[] targetBytes = rtnVal.getBytes();

		Encoder encoder = Base64.getEncoder();
		byte[] encodedBytes = encoder.encode(targetBytes);
		rtnVal = new String(encodedBytes);
		return rtnVal;
	}

	public static String Base64decoder(String val) throws Exception {
		String rtnVal = val;
		byte[] targetBytes = rtnVal.getBytes();

		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(targetBytes);
		rtnVal = new String(decodedBytes);
		return rtnVal;
	}

	public static double isCalcProgress(double param) throws Exception {
		double progress = 0;
		if (param <= 0) {
			progress = 0;
		} else if (param >= 100) {
			progress = 1;
		} else {
			progress = Double.parseDouble(String.format("%.1f", param / 100));
		}
		return progress;
	}

	public static long isCalcProgressLong(long param) throws Exception {
		long progress = 0;
		if (param >= 100) {
			progress = 100;
		} else if (param < 0) {
			progress = 0;
		} else {
			progress = Math.round(param * 100) / 100;
		}
		return progress;
	}

	public static String getUserIp() throws Exception {
		try {
			for (final Enumeration<NetworkInterface> enumerationNetworkInterface = NetworkInterface
					.getNetworkInterfaces(); enumerationNetworkInterface.hasMoreElements();) {
				final NetworkInterface networkInterface = enumerationNetworkInterface.nextElement();
				for (Enumeration<InetAddress> enumerationInetAddress = networkInterface
						.getInetAddresses(); enumerationInetAddress.hasMoreElements();) {
					final InetAddress inetAddress = enumerationInetAddress.nextElement();
					final String ipAddress = inetAddress.getHostAddress();
					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						return ipAddress;
					}
				}
			}
			return null;
		} catch (final Exception e) {
			return null;
		}
	}

	public static String getUserMacIp(String userIp) throws Exception {

		String macAddress = null;
		String command = "ifconfig";

		String osName = System.getProperty("os.name");
		System.out.println("Operating System is " + osName);

		if (osName.startsWith("Windows")) {
			command = "ipconfig /all";
		} else if (osName.startsWith("Linux") || osName.startsWith("Mac") || osName.startsWith("HP-UX")
				|| osName.startsWith("NeXTStep") || osName.startsWith("Solaris") || osName.startsWith("SunOS")
				|| osName.startsWith("FreeBSD") || osName.startsWith("NetBSD")) {
			command = "ifconfig -a";
		} else if (osName.startsWith("OpenBSD")) {
			command = "netstat -in";
		} else if (osName.startsWith("IRIX") || osName.startsWith("AIX") || osName.startsWith("Tru64")) {
			command = "netstat -ia";
		} else if (osName.startsWith("Caldera") || osName.startsWith("UnixWare") || osName.startsWith("OpenUNIX")) {
			command = "ndstat";
		} else {// Note: Unsupported system.
			throw new Exception("The current operating system '" + osName + "' is not supported.");
		}

		Process pid = Runtime.getRuntime().exec(command);
		BufferedReader in = new BufferedReader(new InputStreamReader(pid.getInputStream()));
		Pattern p = Pattern.compile("([\\w]{1,2}(-|:)){5}[\\w]{1,2}");
		while (true) {
			String line = in.readLine();
			if (line == null)
				break;

			Matcher m = p.matcher(line);
			if (m.find()) {
				macAddress = m.group();
				break;
			}
		}
		in.close();
		return macAddress;
	}

	public static Query getOrderSort(Pageable pageable, String sortName, String sortOrder) throws Exception {
		Query Query = new Query().with(pageable);
		if (!"".equals(SystemInfor.isNull(sortName))) {
			if (Globals.SORT_DESC.equals(SystemInfor.isSortOrder(sortOrder))) {
				Query.with(new Sort(Sort.Direction.DESC, sortName));
			} else {
				Query.with(new Sort(Sort.Direction.ASC, sortName));
			}
		} else {
			Query.with(new Sort(Sort.Direction.ASC, "order"));
		}
		return Query;
	}

	public static Query getUidSort(Pageable pageable, String sortName, String sortOrder) throws Exception {
		Query Query = new Query().with(pageable);
		if (!"".equals(SystemInfor.isNull(sortName))) {
			if (Globals.SORT_DESC.equals(SystemInfor.isSortOrder(sortOrder))) {
				Query.with(new Sort(Sort.Direction.DESC, sortName));
			} else {
				Query.with(new Sort(Sort.Direction.ASC, sortName));
			}
		} else {
			Query.with(new Sort(Sort.Direction.DESC, "_id"));
		}
		return Query;
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

}
