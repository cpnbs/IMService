package com.cp.im.infrastructure.constants;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 推送句子.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/4/27 17:26
 */

public class PushSentenceConstants {

  private static final HashMap<Integer, String> SENTENCE_MAP = Maps.newHashMap();

  static {
    SENTENCE_MAP.put(1, "Hi，你知道我在等你吗？");
    SENTENCE_MAP.put(2, "有点想你，可以理我一下吗？");
    SENTENCE_MAP.put(3, "见到你 我的占有欲就超标了");
    SENTENCE_MAP.put(4, "温柔仅供参考 一切请以生气时间为准");
    SENTENCE_MAP.put(5, "可以来陪我说说话吗？");
    SENTENCE_MAP.put(6, "像我这样的小朋友 你一定要把我放在心尖上呐");
    SENTENCE_MAP.put(7, "你是无边的宇宙 我这颗小宇宙 就在你心中转动");
    SENTENCE_MAP.put(8, "你好呀~~");
    SENTENCE_MAP.put(9, "想我的时候摸摸心脏 我在里面哦");
    SENTENCE_MAP.put(10, "在吗");
    SENTENCE_MAP.put(11, "心里还有小鹿在乱撞 你进去小心点");
    SENTENCE_MAP.put(12, "和你捉迷藏 我一定会输的 因为喜欢一个人是藏也藏不住的");
    SENTENCE_MAP.put(13, "你是世间最可爱的小星星 我爱了整个宇宙只为了和你碰头");
    SENTENCE_MAP.put(14, "你为什么不理我？");
    SENTENCE_MAP.put(15, "见到你 我的占有欲就超标了");
    SENTENCE_MAP.put(16, "我是国家分配给你的宝贝 不支持退货");
    SENTENCE_MAP.put(17, "你被捕了 因为你涉嫌喜欢我");
    SENTENCE_MAP.put(18, "为什么不来看我？是我不好玩吗？");
    SENTENCE_MAP.put(19, "把喜欢碾碎了 藏在对你讲的所有废话里");
    SENTENCE_MAP.put(20, "希望你的心情能像星星一样\n" + "常年闪闪发光 偶尔躲躲乌云");
    SENTENCE_MAP.put(21, "我觉得你挺忙的");
    SENTENCE_MAP.put(22, "要不要来听我给你唱首歌啊？");
    SENTENCE_MAP.put(23, "老板 胸口碎大石看不");
    SENTENCE_MAP.put(24, "宝贝~");
    SENTENCE_MAP.put(25, "你喜欢的样子我都有啊");
    SENTENCE_MAP.put(26, "臭宝~最近有读道德经吗？喜欢 老子              吗？");
    SENTENCE_MAP.put(27, "我多希望压倒我的是你 而不是生活");
    SENTENCE_MAP.put(28, "在吗？你在吗？你会一直在吗");
    SENTENCE_MAP.put(29, "我这有个游戏要来玩一玩吗");
    SENTENCE_MAP.put(30, "是志同道合的朋友吗");
    SENTENCE_MAP.put(31, "Hi！扩列！");
    SENTENCE_MAP.put(32, "来呀朋友尬聊我是认真的~");
    SENTENCE_MAP.put(33, "唉");
    SENTENCE_MAP.put(34, "呼叫呼叫");
    SENTENCE_MAP.put(35, "滴滴滴~~要一起打王者吗？");
    SENTENCE_MAP.put(36, "在线求配对呀");
  }

  /**
   * 获得句子列表.
   *
   * @return 句子列表
   * @author wangcaiwen
   * @since 2021/4/28 9:29
   */
  public static List<String> getSentenceList() {
    List<Integer> indexList = Lists.newLinkedList();
    Integer index = ThreadLocalRandom.current().nextInt(SENTENCE_MAP.size());
    while (indexList.size() < 3) {
      if (!indexList.contains(index)) {
        indexList.add(index);
      }
      index = ThreadLocalRandom.current().nextInt(SENTENCE_MAP.size());
    }
    return indexList.stream().map(key -> SENTENCE_MAP.get(key))
        .collect(Collectors.toCollection(Lists::newLinkedList));
  }

}
