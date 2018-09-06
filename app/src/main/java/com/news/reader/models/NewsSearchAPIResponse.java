
package com.news.reader.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsSearchAPIResponse {

    private String type;
    private String didUMean;
    private Integer totalCount;
    private List<String> relatedSearch = null;
    private List<Value> value = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDidUMean() {
        return didUMean;
    }

    public void setDidUMean(String didUMean) {
        this.didUMean = didUMean;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<String> getRelatedSearch() {
        return relatedSearch;
    }

    public void setRelatedSearch(List<String> relatedSearch) {
        this.relatedSearch = relatedSearch;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
