package org.crypto.cache;

import org.crypto.model.Threshold;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
      includeClasses = Threshold.class,
      schemaFileName = "threshold.proto", 
      schemaFilePath = "proto/", 
      schemaPackageName = "org_crypto_model")
interface ThresholdIntializer extends SerializationContextInitializer {
}