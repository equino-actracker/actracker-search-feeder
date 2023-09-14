package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagIndex;

final class ConsoleTagIndex
        extends ConsoleEntityIndex<TagId, Tag, TagGraph>
        implements TagIndex {
}
