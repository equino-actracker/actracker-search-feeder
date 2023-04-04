package ovh.equino.actracker.searchfeed.application.tag;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.tag.TagIndexer;

public class TagService {

    private final TagIndexer tagIndexer;

    TagService(TagIndexer tagIndexer) {
        this.tagIndexer = tagIndexer;
    }

    public void indexTag(IndexTagCommand indexTagCommand) {
        Tag tag = new Tag(
                new TagId(indexTagCommand.id()),
                new Version(indexTagCommand.version()),
                indexTagCommand.softDeleted(),
                indexTagCommand.name()
        );

        tagIndexer.indexTag(tag);
    }

}
