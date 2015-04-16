package daiq.application;

import daiq.core.lang.ApplicationService;

public class AdminAppService implements ApplicationService {
    
    public String healthCheck() {
        return "OK";
    }
}
