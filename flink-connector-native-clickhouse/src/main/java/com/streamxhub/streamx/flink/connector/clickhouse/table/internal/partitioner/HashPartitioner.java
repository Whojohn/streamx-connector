/*
 * Copyright (c) 2019 The StreamX Project
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.streamxhub.streamx.flink.connector.clickhouse.table.internal.partitioner;

import org.apache.flink.table.data.RowData;

import java.util.Objects;

/**
 * @author benjobs
 */
public class HashPartitioner implements ClickHousePartitioner {
    private static final long serialVersionUID = 1L;

    private RowData.FieldGetter getter;

    public HashPartitioner(RowData.FieldGetter getter) {
        this.getter = getter;
    }

    @Override
    public int select(RowData record, int numShards) {
        return Objects.hashCode(this.getter.getFieldOrNull(record)) % numShards;
    }
}