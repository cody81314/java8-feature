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

Stream 讓你從外部迭代轉向內部迭代。如此一來就不用寫項如下這樣的程式碼來管理資料集合（外部迭代）的迭代了：

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


### 篩選 和 切片

1. 用函數篩選

Streams 介面支援 filter 方法。該方法會接受一個回傳 boolean 值的函數作為參數，並且回傳一個包含所有符合此函數邏輯的元素的 Stream ，如下，從一張菜單篩選出所有的素菜：

```java
List<Dish> vegetarianMenu = menu.stream()
                                .filter(Dish::isVegetarian)
                                .collect(toList());
```

2. 篩選各異的元素

