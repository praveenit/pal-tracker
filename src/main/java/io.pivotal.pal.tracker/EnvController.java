package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    private final String port;
    private final String memoryLimit;
    private final String cfInstanceIndex;
    private final String cfInstanceAddress;



    public EnvController(@Value("${PORT:NOT SET}") String port,
    @Value("${MEMORY_LIMIT:NOT SET}") String memoryLimit,
    @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstanceIndex,
    @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddress)
    {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;
    }

    @GetMapping("/std")
    public EnvPojo getEnv() {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);

        return new EnvPojo("8675",
                "12G",
                "34",
                "123.sesame.street");
    }

    class EnvPojo{

        private String port;
        private String memoryLimit;
        private String cfInstanceIndex;
        private String cfInstanceAddress;

        public EnvPojo(String port,String memoryLimit,String cfInstanceIndex, String cfInstanceAddress)
        {
            this.port = port;
            this.memoryLimit = memoryLimit;
            this.cfInstanceIndex = cfInstanceIndex;
            this.cfInstanceAddress = cfInstanceAddress;

        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getMemoryLimit() {
            return memoryLimit;
        }

        public void setMemoryLimit(String memoryLimit) {
            this.memoryLimit = memoryLimit;
        }

        public String getCfInstanceIndex() {
            return cfInstanceIndex;
        }

        public void setCfInstanceIndex(String cfInstanceIndex) {
            this.cfInstanceIndex = cfInstanceIndex;
        }

        public String getCfInstanceAddress() {
            return cfInstanceAddress;
        }

        public void setCfInstanceAddress(String cfInstanceAddress) {
            this.cfInstanceAddress = cfInstanceAddress;
        }
    }

}
