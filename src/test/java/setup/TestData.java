package setup;

import java.util.Map;

public class TestData {
    public static final String BASE_URL = "https://api.restful-api.dev/objects";
    public static final int DEFAULT_YEAR = 2019;
    public static final double DEFAULT_PRICE = 1849.99;
    public static final String DEFAULT_CPU_MODEL = "Intel Core i9";
    public static final String DEFAULT_HARD_DISK_SIZE = "1 TB";
    public static final String DEFAULT_ITEM_NAME = "Apple MacBook Pro 16";

    public static final Map<String, Object> DEFAULT_ITEM_DATA_JSON = Map.ofEntries(
            Map.entry("year", DEFAULT_YEAR),
            Map.entry("price", DEFAULT_PRICE),
            Map.entry("CPU Model", DEFAULT_CPU_MODEL),
            Map.entry("Hard Disk Size", DEFAULT_HARD_DISK_SIZE)
    );

}
