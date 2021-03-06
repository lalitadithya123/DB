package edu.unika.aifb.dbsqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ho.yaml.Yaml;


public class Config {
	
	private static final Logger log = Logger.getLogger(Config.class);

	//Server information
	private String m_server = Environment.DEFAULT_SERVER;
	private String m_username = Environment.DEFAULT_USERNAME;
	private String m_password = Environment.DEFAULT_PASSWORD;
	private String m_dbname = Environment.DEFAULT_DATABASE_NAME; 
	private String m_port = Environment.DEFAULT_PORT;

	//Data files
	private List<String> m_files;
	private String m_dsFile;
	private int m_dsFrom;
	private int m_dsEnd;
  
	//Configuration information
	private int m_maxDistance = Environment.DEFAULT_MAX_DISTANCE;
	private int m_topKeyword = Environment.DEFAULT_TOP_KEYWORD;
	private int m_topDatabase = Environment.DEFAULT_TOP_DATABASE;
	
	//Temporary directory
	private String m_tempDir = Environment.DEFAULT_TEMPORAL_FILEPATH;
	private String m_stopwordFilePath = Environment.DEFAULT_STOPWORD_FILEPATH; 
	
	//singleton
	public static Config single = null;
	public static String configFilePath = null;

	public static void setConfigFilePath(String configFileName) {
		configFilePath = configFileName;
	}
	public static Config getConfig() {
		if(single == null) {
			single = new Config(configFilePath);
		}
		return single;
	}
	
	private Config(String configFileName) {
		load(configFileName);
	}
	
	public void load(String configFileName) {
		Map config = null;
		try {
			config = (Map)Yaml.load(new File(configFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		m_server = config.get("server") != null ? (String)config.get("server") : Environment.DEFAULT_SERVER;
		m_username = config.get("username") != null ? (String)config.get("username") : Environment.DEFAULT_USERNAME;
		m_password = config.get("password") != null ? (String)config.get("password") : Environment.DEFAULT_PASSWORD;	
		m_dbname = config.get("dbname") != null ? (String)config.get("dbname") : Environment.DEFAULT_DATABASE_NAME;	
		m_port = config.get("port") != null ? ((Integer)config.get("port")).toString() : Environment.DEFAULT_PORT;
		m_maxDistance = config.get("maxDistance") != null ? (Integer)config.get("maxDistance") : Environment.DEFAULT_MAX_DISTANCE;
		m_topKeyword = config.get("topKeyword") != null ? (Integer)config.get("topKeyword") : Environment.DEFAULT_TOP_KEYWORD;
		m_topDatabase = config.get("topDatabase") != null ? (Integer)config.get("topDatabase") : Environment.DEFAULT_TOP_DATABASE;	
		m_tempDir = config.get("tempDirectory") != null ? (String)config.get("tempDirectory") : Environment.DEFAULT_TEMPORAL_FILEPATH;
		m_files = (List<String>)config.get("files");
		m_dsFile = config.get("dsFile") != null ? (String)config.get("dsFile") : Environment.DEFAULT_DS_FILEPATH;	
		m_dsFrom = config.get("dsFrom") != null ? (Integer)config.get("dsFrom") : Environment.DEFAULT_DS_FROM;
		m_dsEnd = config.get("dsEnd") != null ? (Integer)config.get("dsEnd") : Environment.DEFAULT_DS_END;
		m_stopwordFilePath = config.get("stopword") != null ? (String)config.get("stopword") : Environment.DEFAULT_STOPWORD_FILEPATH;
	}
	
	public String getDbServer() {
		return m_server;
	}
	
	public String getDbUsername() {
		return m_username;
	} 
	
	public String getDbPassword() {
		return m_password;
	}
	
	public String getDbName() {
		return m_dbname;
	}
	
	public String getDbPort() {
		return m_port; 
	}
	
	public int getMaxDistance() {
		return m_maxDistance;
	}
	
	public int getTopKeyword() {
		return m_topKeyword;
	} 
	
	public int getTopDatabase() {
		return m_topDatabase;
	}
	
	public List<String> getDataFiles() {
		return m_files;
	}
	
	public String getDsFile() {
		return m_dsFile;
	} 
	
	public int getDsFromNum() {
		return m_dsFrom;
	} 
	
	public int getDsEndNum() {
		return m_dsEnd;
	} 
	
	public String getTemporaryDirectory() {
		return m_tempDir;
	}
	
	public String getStopwordFilePath() {
		return m_stopwordFilePath;
	}
	
}