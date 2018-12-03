package com.ef;

import java.util.Arrays;
import java.util.List;

public class ParserConstants {

   public static final List<String> ENV_NAMES = Arrays.asList( "local", "dev", "uat", "pts", "prd" );
   public static final String[]  FILE_FIELDS = new String[]{ "date", "IP", "request", "status", "userAgent" };

   public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
   public static final String DELIMITER = "|";
   public static final String LOG_FILE_NAME= "access.log";
   public static final String LOAD_LOG_FILE = "LOAD_LOG_FILE ";
   public static final String LOAD_LOG = "LOAD_LOG ";
   public static final String ACCESS_LOG_READER = "ACCESS_LOG_READER ";
   public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
   public static final String BLOCK_MESSAGE = "IP is blocked because it has too many requests";
}
