package ovh.equino.actracker.searchfeed.application.tagset;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.services.tagset.TagSetProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

public class TagSetService {

    private final TagSetProcessor tagSetProcessor;

    TagSetService(TagSetProcessor tagSetProcessor) {
        this.tagSetProcessor = tagSetProcessor;
    }

    public void indexTagSet(IndexTagSetCommand indexTagSetCommand) {
        TagSet tagSet = new TagSet(
                new TagSetId(indexTagSetCommand.id()),
                new Version(indexTagSetCommand.version()),
                indexTagSetCommand.softDeleted(),
                new CreatorId(indexTagSetCommand.creatorId()),
                indexTagSetCommand.name(),
                toTagIds(indexTagSetCommand.tags())
        );

        tagSetProcessor.processTagSet(tagSet);
    }

    private Set<TagId> toTagIds(Collection<UUID> tagUuids) {
        return requireNonNullElse(tagUuids, new ArrayList<UUID>())
                .stream()
                .map(TagId::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
