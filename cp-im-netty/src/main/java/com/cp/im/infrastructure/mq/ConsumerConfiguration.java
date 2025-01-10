package com.cp.im.infrastructure.mq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ConsumerConfiguration
 **/
@Component
@ConfigurationProperties(prefix = "consumers")
public class ConsumerConfiguration {
    private String topic;

    public static class listeners {

        public static class registeredListener {
            private String tag;
            private String group;

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getGroup() {
                return group;
            }

            public void setGroup(String group) {
                this.group = group;
            }
        }

        public static class modifyListener {
            private String tag;
            private String group;

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getGroup() {
                return group;
            }

            public void setGroup(String group) {
                this.group = group;
            }
        }



    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
