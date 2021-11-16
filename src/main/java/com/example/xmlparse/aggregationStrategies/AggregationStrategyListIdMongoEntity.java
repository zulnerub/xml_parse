package com.example.xmlparse.aggregationStrategies;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class AggregationStrategyListIdMongoEntity implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String newObjectId = newExchange.getIn().getBody(String.class);
        newObjectId = newObjectId.substring(newObjectId.indexOf("\"isbn\":") + 8, newObjectId.indexOf("\"isbn\":") + 12);

        if (oldExchange == null) {
            newExchange.getIn().setBody(newObjectId);
            return newExchange;
        }

        String objectIds = oldExchange.getIn().getBody(String.class);

        objectIds += "," + newObjectId;

        oldExchange.getIn().setBody(objectIds);

        return oldExchange;
    }
}
