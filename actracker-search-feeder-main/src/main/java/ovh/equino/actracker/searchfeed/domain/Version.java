package ovh.equino.actracker.searchfeed.domain;

public record Version(long version) {

    public boolean isLaterThan(Version otherVersion) {
        return this.version > otherVersion.version;
    }
}
