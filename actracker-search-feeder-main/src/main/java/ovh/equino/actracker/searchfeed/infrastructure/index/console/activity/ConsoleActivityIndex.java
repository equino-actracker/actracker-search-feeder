package ovh.equino.actracker.searchfeed.infrastructure.index.console.activity;

import ovh.equino.actracker.searchfeed.domain.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.activity.ActivityIndex;

class ConsoleActivityIndex implements ActivityIndex {

    @Override
    public void index(Activity activity) {
        System.out.printf("Indexed activity: %s%n", activity.id());
    }
}
