package ru.nsu.ccfit.chernovskaya.factory.config_parser;

import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConfigParser {

    private static String CONFIG_PROPERTIES_FILE_NAME = "config.properties";
    private static Properties configProperties;

    public ConfigParser(){}
    public ConfigParser(String fileName){
        CONFIG_PROPERTIES_FILE_NAME = fileName;
    }

    public void readProperties(){
        log.info("Reading config file...");

        try (InputStream configStream = ConfigParser.class.getClassLoader()
                .getResourceAsStream(CONFIG_PROPERTIES_FILE_NAME)) {

            if (configStream == null) {
                throw new NullPointerException("not such file");
            }
            configProperties = new Properties();
            configProperties.load(configStream);

        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }

    public int getSupplierDelay(){
        int supplierDelay = Integer.parseInt(configProperties.getProperty("SupplierDelay", "0"));
        return supplierDelay;
    }

    public int getDealerDelay(){
        int dealerDelay = Integer.parseInt(configProperties.getProperty("DealerDelay", "0"));
        return dealerDelay;
    }

    public int getWarehouseBodyCapacity(){
        int warehouseBodyCapacity = Integer.parseInt(configProperties.getProperty("WarehouseBodyCapacity", "0"));
        return warehouseBodyCapacity;
    }

    public int getWarehouseMotorCapacity(){
        int warehouseMotorCapacity = Integer.parseInt(configProperties.getProperty("WarehouseMotorCapacity", "0"));
        return warehouseMotorCapacity;
    }

    public int getWarehouseAccessoryCapacity(){
        int warehouseAccessoryCapacity = Integer.parseInt(configProperties.getProperty("WarehouseAccessoryCapacity", "0"));
        return warehouseAccessoryCapacity;
    }

    public int getWarehouseAutoCapacity(){
        int warehouseAutoCapacity = Integer.parseInt(configProperties.getProperty("WarehouseAutoCapacity", "0"));
        return warehouseAutoCapacity;
    }

    public int getSupplierCount(){
        int supplierCount = Integer.parseInt(configProperties.getProperty("Suppliers", "0"));
        return supplierCount;
    }

    public int getWorkerCount(){
        int workerCount = Integer.parseInt(configProperties.getProperty("Workers", "0"));
        return workerCount;
    }

    public int getDealerCount(){
        int dealerCount = Integer.parseInt(configProperties.getProperty("Dealers", "0"));
        return dealerCount;
    }

    public boolean getLogSale(){
        boolean logSaleStatus = Boolean.parseBoolean(configProperties.getProperty("LogSale", "true"));
        return logSaleStatus;
    }

}