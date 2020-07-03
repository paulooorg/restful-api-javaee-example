package io.github.paulooorg.api.resources;

import io.github.paulooorg.api.model.dto.DTO;

public interface GenericResource<D extends DTO> extends ListResource<D>, FormResource<D> {

}
