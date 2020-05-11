package faber.codetest.nhat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import faber.codetest.nhat.model.ResponseDto;
import faber.codetest.nhat.service.AbstractCrudService;

import java.util.Collection;

public abstract class AbstractController<T, S> {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    /**
     * @return““
     */
    @GetMapping
    protected ResponseEntity<ResponseDto> getDefault() {
        LOGGER.debug("GetMapping in {}", this.getClass().getSimpleName());
        try {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(Boolean.TRUE);
            responseDto.setData(getDefaultList());

            return ResponseEntity.ok(responseDto);
        } catch (Exception ex) {
            LOGGER.error("Exception in getDefault ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * get by id
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable(value = "id") S id) {
        ResponseDto responseDto = new ResponseDto();

        T object = getCrudService().findById(id);
        if (object != null) {
            responseDto.setSuccess(Boolean.TRUE);
            responseDto.setData(object);
            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * create / update object
     * @param object
     * @return
     */
    @PostMapping
    protected ResponseEntity<ResponseDto> persist(@RequestBody T object) {
        LOGGER.debug("PostMapping in {}", this.getClass().getSimpleName());
        ResponseDto responseDto = new ResponseDto();
        try {
            getCrudService().save(object);
            responseDto.setSuccess(Boolean.TRUE);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            LOGGER.error("Cannot persist {}", this.getClass().getSimpleName());
            responseDto.setSuccess(Boolean.FALSE);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    /**
     * delete by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    protected ResponseEntity<ResponseDto> delete(@PathVariable(value = "id") S id) {
        LOGGER.debug("DeleteMapping in {}", this.getClass().getSimpleName());

        getCrudService().deleteById(id);
        ResponseDto responseDto =  new ResponseDto();
        responseDto.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * get service instance to do persistence
     * @return
     */
    public abstract AbstractCrudService<T, S> getCrudService();

    /**
     * get list in default path(/)
     * @return
     */
    public abstract Collection<T> getDefaultList();

}
