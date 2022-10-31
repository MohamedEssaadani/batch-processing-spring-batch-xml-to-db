package org.essaadani.batchprocessingspringbatchxml.Constantes;

public class ImportUsersConst {
    public static String JOB_NAME = "xml-to-db-users-job";
    public static String XML_TO_DATABASE_STEP = "xmlFileToDatabaseStep";

    public static final String QUERY_INSERT_USER = "INSERT " +
            "INTO users(id, name) " +
            "VALUES (?, ?)";
}
