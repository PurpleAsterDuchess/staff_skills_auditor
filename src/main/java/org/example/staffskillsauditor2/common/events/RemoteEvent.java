package org.example.staffskillsauditor2.common.events;

public interface RemoteEvent extends Event {
    String exchange();
    String routingKey();
}

