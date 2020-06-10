package com.octoperf.kraken.runtime.context.gatling.environment.publisher;

import com.octoperf.kraken.config.analysis.client.api.AnalysisClientProperties;
import com.octoperf.kraken.runtime.context.api.environment.EnvironmentPublisher;
import com.octoperf.kraken.runtime.context.entity.ExecutionContextBuilder;
import com.octoperf.kraken.runtime.entity.environment.ExecutionEnvironmentEntry;
import com.octoperf.kraken.runtime.entity.task.TaskType;
import com.octoperf.kraken.runtime.entity.environment.ExecutionEnvironmentEntrySource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.octoperf.kraken.tools.environment.KrakenEnvironmentKeys.KRAKEN_ANALYSIS_URL;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class AnalysisUrlPublisher implements EnvironmentPublisher {

  @NonNull AnalysisClientProperties properties;

  @Override
  public boolean test(final TaskType taskType) {
    return test(taskType, TaskType.GATLING_DEBUG, TaskType.GATLING_RECORD);
  }

  @Override
  public Mono<List<ExecutionEnvironmentEntry>> apply(final ExecutionContextBuilder context) {
    return Mono.just(of(
        ExecutionEnvironmentEntry.builder().from(ExecutionEnvironmentEntrySource.BACKEND).scope("").key(KRAKEN_ANALYSIS_URL.name()).value(properties.getUrl()).build()
    ));
  }
}