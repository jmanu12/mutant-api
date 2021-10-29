/*
 * Copyright (c) 2021 juan Fajardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.mutant.mapper;
import java.io.Serializable;

/**
 * The <strong>DtoConverter</strong> interface contains all methods to convert the entities to data
 * transfer objects and vice versa.
 *
 * @param <E> the entity class
 * @param <Q> the data transfer object to request
 * @param <S> the data transfer object ot respond
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
public interface DtoMapper<E extends Serializable, Q, S> {

    /**
     * Convert the data transfer object of the request to an entity.
     *
     * @param data the data transfer object
     * @return entity
     */
    E mapToDocument(Q data);

    /**
     * Convert the entity to a data transfer object of the respond.
     *
     * @param entity the entity
     * @return data transfer object
     */
    S mapToDto(E entity);
}
