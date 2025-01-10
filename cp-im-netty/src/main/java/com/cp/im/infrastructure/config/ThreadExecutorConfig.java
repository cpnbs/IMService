package com.cp.im.infrastructure.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程执行器配置.
 *
 * @author xubin
 * @version v2.0.0
 * @since 2020/7/31 13:10
 */

@Slf4j
@Configuration
public class ThreadExecutorConfig {

  /**
   * 核心线程数.
   */
  private static final int CORE_POOL_SIZE = 10;

  /**
   * 最大线程数.
   */
  private static final int MAX_POOL_SIZE = 100;

  /**
   * 队列数.
   */
  private static final int QUEUE_CAPACITY = 200;

  @Bean
  public ExecutorService taskFxbDrawExecutor() {
    log.info("开始加载 taskFxbDrawExecutor... ");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(CORE_POOL_SIZE);
    executor.setMaxPoolSize(MAX_POOL_SIZE);
    executor.setQueueCapacity(QUEUE_CAPACITY);
    executor.setThreadNamePrefix("task-fxb-draw-service-");
    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    // 执行初始化
    executor.initialize();
    return executor.getThreadPoolExecutor();
  }

}
