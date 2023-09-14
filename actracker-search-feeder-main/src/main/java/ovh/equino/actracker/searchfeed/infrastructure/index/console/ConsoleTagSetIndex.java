package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetGraph;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetIndex;

class ConsoleTagSetIndex extends ConsoleEntityIndex<TagSetId, TagSetGraph> implements TagSetIndex {
}
