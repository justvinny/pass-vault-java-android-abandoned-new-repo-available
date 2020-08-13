package com.github.justvinny.passvault.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlatformAccount that = (PlatformAccount) o;
        return Objects.equals(platformName, that.platformName) &&
                Objects.equals(platformUsername, that.platformUsername) &&
                Objects.equals(platformPassword, that.platformPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platformName, platformUsername, platformPassword);
    }

    public static PlatformAccount platformAccountFactory(
            String platformName, String platformUsername, String platformPassword) {
        return new PlatformAccount(platformName, platformUsername, platformPassword);
    }
}
