package dk.sdu.tekvideo

class Video implements Node {
    String name
    String youtubeId
    String timelineJson
    String description = "Ingen beskrivelse"
    Boolean videoType = true
    Date dateCreated
    NodeStatus localStatus = NodeStatus.VISIBLE
    Long videos_idx

    static constraints = {
        name            nullable: false, blank: false
        youtubeId       nullable: false, blank: false
        timelineJson    nullable: true
        description     nullable: true
        videos_idx      nullable: true
    }

    static belongsTo = [subject: Subject]
    static hasMany = [comments: Comment]

    static mapping = {
        timelineJson type: "text"
        videoType defaultValue: true
        subject nullable: true
        videos_idx updateable: false, insertable: false
    }

    static jsonMarshaller = { Video it ->
        [
                id          : it.id,
                name        : it.name,
                youtubeId   : it.youtubeId,
                timelineJson: it.timelineJson,
                description : it.description,
                videoType   : it.videoType,
                dateCreated : it.dateCreated,
        ]
    }

    String getDescription() {
        if (description == null) return "Ingen beskrivelse"
        return description
    }

    String getTimelineJson() {
        if (timelineJson == null) return "[]"
        return timelineJson
    }

    @Override
    Node getParent() {
        subject
    }

}
