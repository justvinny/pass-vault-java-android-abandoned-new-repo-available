package com.github.justvinny.passvault.domain;

public class PlatformAccount {
    private String platformName;
    private String platformUsername;
    private String platformPassword;

    public PlatformAccount(String platformName, String platformUsername, String platformPassword) {
        this.platformName = platformName;
        this.platformUsername = platformUsername;
        this.platformPassword = platformPassword;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformUsername() {
        return platformUsername;
    }

    public String getPlatformPassword() {
        return platformPassword;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", platformName, platformUsername, platformPassword);
    }

    public static PlatformAccount platformAccountFactory(
            String platformName, String platformUsername, String platformPassword) {
        return new PlatformAccount(platformName, platformUsername, platformPassword);
    }
}
