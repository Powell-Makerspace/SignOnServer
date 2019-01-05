package org.powellmakerspace.sign_on_server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for the {@link org.powellmakerspace.sign_on_server.util.DatabaseSeeder} utility
 *
 * Values below can be specified in the application.properties file to specify a value for specific environments, for
 * example, to disable the database seeder, the following can be added to the application.properties file
 *
 * <code>
 *     seeder.enable=false
 * </code>
 *
 * Values can also be overridden by environment vars at runtime, for example:
 *
 * <code>
 *     export SEEDER_ENABLE=false
 * </code>
 *
 */
@ConfigurationProperties(prefix = "seeder")
@Configuration
public class DbSeederProperties {

    /**
     * Indicates whether or not the database seeder should run at startup
     */
    private boolean enable;

    /**
     * Indicates number of random members to generate (has no effect if "enable" is false)
     */
    private int numberOfMembers;

    /**
     * Indicates the number of random visits to generate (has no effect if "enable" is false)
     */
    private int numberOfVisits;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }
}
