package ch.bfh.wickedcoders.wickedboard.service.impl;

import ch.bfh.wickedcoders.wickedboard.entities.Label;
import ch.bfh.wickedcoders.wickedboard.repository.LabelRepository;
import ch.bfh.wickedcoders.wickedboard.service.LabelService;
import ch.bfh.wickedcoders.wickedboard.service.dto.LabelDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * default implementation of {@link ch.bfh.wickedcoders.wickedboard.service.LabelService}.
 *
 * @label pfafs1
 */
@Named
public class DefaultLabelService implements LabelService {
    @Inject
    private LabelRepository labelRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public LabelDTO create(LabelDTO labelDTO) {
        Label label = mapper.map(labelDTO, Label.class);
        Label persistedLabel = labelRepository.save(label);
        return mapper.map(persistedLabel, LabelDTO.class);
    }

    @Override
    public LabelDTO read(String name) {
        Label label = labelRepository.findOne(name);
        if (label == null) return null;
        return mapper.map(label, LabelDTO.class);
    }

    @Override
    public Collection<LabelDTO> list() {
        Iterable<Label> labels = labelRepository.findAll();
        Type listType = new TypeToken<Collection<LabelDTO>>() {
        }.getType();
        return mapper.map(labels, listType);
    }

    @Override
    public LabelDTO update(LabelDTO labelDTO) {
        Label label = mapper.map(labelDTO, Label.class);
        Label updatedLabel = labelRepository.save(label);
        return mapper.map(updatedLabel, LabelDTO.class);
    }

    @Override
    public void delete(LabelDTO labelDTO) {
        Label label = labelRepository.findOne(labelDTO.getName());
        labelRepository.delete(label);
    }
}
