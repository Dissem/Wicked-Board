package ch.bfh.wickedcoders.wickedboard.service.impl;

import ch.bfh.wickedcoders.wickedboard.entities.Topic;
import ch.bfh.wickedcoders.wickedboard.repository.TopicRepository;
import ch.bfh.wickedcoders.wickedboard.service.TopicService;
import ch.bfh.wickedcoders.wickedboard.service.dto.TopicDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
public class DefaultTopicService implements TopicService {

    private ModelMapper mapper = new ModelMapper();
    @Inject
    private TopicRepository topicRepository;

    @Override
    public TopicDTO create(TopicDTO topicDto) {
        Topic topic = mapper.map(topicDto, Topic.class);
        topic = topicRepository.save(topic);
        return mapper.map(topic, TopicDTO.class);
    }

    @Override
    public TopicDTO read(long id) {
        Topic topic = topicRepository.findOne(id);
        if (topic == null) return null;
        return mapper.map(topic, TopicDTO.class);
    }

    @Override
    public Collection<TopicDTO> list(final Integer parentId) {
        return mapper.map(
                topicRepository.findByParentId(parentId),
                new TypeToken<Collection<TopicDTO>>() {
                }.getType());
    }

    @Override
    public TopicDTO update(TopicDTO topicDto) {
        Topic topic = mapper.map(topicDto, Topic.class);
        Topic updatedTopic = topicRepository.save(topic);
        return mapper.map(updatedTopic, TopicDTO.class);
    }

    @Override
    public void delete(TopicDTO topicDto) {
        Topic book = topicRepository.findOne(topicDto.getId());
        topicRepository.delete(book);
    }
}
