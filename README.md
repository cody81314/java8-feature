AB
# java8-feature

Learning Java 8 features and implement example code.

## 為何要學習 Java 8 的新特性？

1.  現有的 Java 實作並不能很好地利用多核處理器
2.  函數式編寫使程式碼更簡潔易讀
3.  Java8 中 Stream 的概念，讓程式碼更簡潔易讀，並支援並行處理 stream 元素
4.  可以在介面中使用默認方法，在實作類別沒有實作方法時提供預設的方法內容
5.  其他來自 function style 的概念，包括處理 null 和使用模式匹配

## 透過行為參數化傳遞程式碼
.......


## 第五章 使用Stream

Stream 讓你從外部迭代轉向內部迭代。如此一來就不用寫項如下這樣的程式碼來管理資料集合（外部迭代）的迭代了：

```java
List<Dish> vegetarianDishes = new ArrayList<>();
for(Dish d : menu) {
    if(d.isVegetarian()) {
        vegetarianDishes.add(d);
    }
}
```

可以改為使用支援 filter 和 collect 操作的 Stream API （內部迭代）來管理資料集合的迭代：

```java
import static java.util.stream.Collectors.toList;
List<Dish> vegetarianDishes = 
    menu.stream()
        .filter(Dish::isVegetarian)
        .collect(toList());
```

這種處理資料的方式讓 Stream API 管理如何處理資料。 Stream API 可以在背後進行多種優化。此外，使用內部迭代， Stream API 可以決定並行運行你的程式碼。而外部迭代只能單一執行緒一個一個迭代並依序執行程式碼。


### 篩選 和 切片

1. 用函數篩選

Streams 介面支援 filter 方法。該方法會接受一個回傳 boolean 值的函數作為參數，並且回傳一個包含所有符合此函數邏輯的元素的 Stream ，如下，從一張菜單篩選出所有的素菜：

```java
List<Dish> vegetarianMenu = menu.stream()
                                .filter(Dish::isVegetarian)
                                .collect(toList());
```

2. 篩選各異的元素

Stream 還支援一個叫 distinct 的方法，他會回傳一個元素各異的 Stream。如下，選出列表中所有偶數，並確保沒有重複。

```java
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream()
    .filter(i -> i % 2 == 0)
    .distinct()
    .forEach(System.out::println);
```

3. 截短 Stream

Stream 支援 limit(n) 方法，他會回傳一個不超過給定長度的 Stream。如下，建立一個 List，選出熱量超過300卡的頭三道菜。

```java
List<Dish> dishes = menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .limit(3)
                        .collect(toList());
```

4. 跳過元素

Stream 還支援 skip(n) 方法，他會回傳一個丟掉前 n 個元素的 Stream。如果 Stream 不足 n 個元素，則回傳一個空的 Stream。如下，跳過超過300卡的頭兩道菜，並且回傳剩下的。

```java
List<Dish> dishes = menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());
```

### 映射

1. 對 Stream 中每一個元素套用函數

Stream 支援 map 方法，它接受一個函數作為參數。這個函數將會被套用在 Stream 中的每一個元素上，並且將其映射成一個新的元素。如下，把方法引用 Dish::getName 傳給 map 方法，取得 Stream 中元素的名稱。

```java
Stream<String> dishNames = menu.stream()
                               .map(Dish::getName);
```

2. Stream 的扁平化

有時我們可能會遇到一個情況，如下：

```java
List<String> words = Arrays.asList("Hello", "World");
Stream<String[]> chars = words.stream()
                              .map(word -> word.split(""));
```

但我們真正想要的是用 Stream\<String> 來表示一個字符流，可以用 flatMap 方法來達到這個需求。

```java
List<String> words = Arrays.asList("Hello", "World");
Stream<String> chars = words.stream()
                            .map(word -> word.split(""))
                            .flatMap(Arrays::stream);   //Arrays.stream() 可以將一個 Array 轉成 Stream
```

使用 flatMap 方法的效果是，將套用 Arrays::stream 的所有元素各自生成的 Stream 合併成為一個 Stream。

