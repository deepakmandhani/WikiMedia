
package com.wiki.reader.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WikiSearchAPIResponse {
        private boolean batchcomplete;

        public boolean getBatchcomplete() { return this.batchcomplete; }

        public void setBatchcomplete(boolean batchcomplete) { this.batchcomplete = batchcomplete; }

/*        private Continue continue;

        public Continue getContinue() { return this.continue; }

        public void setContinue(Continue continue) { this.continue = continue; }*/

        private Query query;

        public Query getQuery() { return this.query; }

        public void setQuery(Query query) { this.query = query; }
}


