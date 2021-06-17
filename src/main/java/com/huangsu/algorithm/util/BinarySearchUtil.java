package com.huangsu.algorithm.util;

/**
 * Created by huangsu2012@gmail.com on 2018/3/28.
 */
public class BinarySearchUtil {

  /**
   * 查找等于给定元素值的元素
   *
   * @param items 待查找元素列表
   * @param toFind 待查找元素
   * @param <T> 继承Comparable接口的实体
   * @return 等于给定元素值的元素的下标
   */
  public static <T extends Comparable<T>> int find(T[] items, T toFind) {
    if (items != null && items.length > 0 && toFind != null) {
      int low = 0;
      //此处如果不减一，当toFind大于数组任意元素时将抛出ArrayIndexOutOfBoundsException,如果不减一就把循环条件改成low<high也行
      int high = items.length - 1;
      for (; low <= high; ) {
        int mid = low + (high - low) / 2;
        T midItem = items[mid];
        int compareVal = midItem.compareTo(toFind);
        if (compareVal == 0) {
          return mid;
        } else if (compareVal < 0) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  /**
   * 查找第一个等于给定元素值的元素
   *
   * @param items 待查找元素列表
   * @param toFind 待查找元素
   * @param <T> 继承Comparable接口的实体
   * @return 第一个等于给定元素值的元素的下标
   */
  public static <T extends Comparable<T>> int findFirst(T[] items, T toFind) {
    if (items != null && items.length > 0 && toFind != null) {
      int low = 0;
      int high = items.length - 1;
      for (; low <= high; ) {
        int mid = low + (high - low) / 2;
        T midItem = items[mid];
        int compareVal = midItem.compareTo(toFind);
        if (compareVal < 0) {
          low = mid + 1;
        } else if (compareVal > 0) {
          high = mid - 1;
        } else {
          if (mid == 0 || items[mid - 1].compareTo(toFind) != 0) {
            return mid;
          }
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  /**
   * 查找最后一个等于给定元素值的元素
   *
   * @param items 待查找元素列表
   * @param toFind 待查找元素
   * @param <T> 继承Comparable接口的实体
   * @return 最后一个等于给定元素值的元素的下标
   */
  public static <T extends Comparable<T>> int findLast(T[] items, T toFind) {
    if (items != null && items.length > 0 && toFind != null) {
      int low = 0;
      int high = items.length - 1;
      for (; low <= high; ) {
        int mid = low + (high - low) / 2;
        T midItem = items[mid];
        int compareVal = midItem.compareTo(toFind);
        if (compareVal < 0) {
          low = mid + 1;
        } else if (compareVal > 0) {
          high = mid - 1;
        } else {
          if (mid == items.length - 1 || items[mid + 1].compareTo(toFind) != 0) {
            return mid;
          }
          low = mid + 1;
        }
      }
    }
    return -1;
  }

  /**
   * 查找第一个大于给定元素值的元素
   *
   * @param items 待查找元素列表
   * @param toFind 待查找元素
   * @param <T> 继承Comparable接口的实体
   * @return 第一个大于给定元素值的元素的下标
   */
  public static <T extends Comparable<T>> int findGEFirst(T[] items, T toFind) {
    if (items != null && items.length > 0 && toFind != null) {
      int low = 0;
      int high = items.length - 1;
      for (; low <= high; ) {
        int mid = low + (high - low) / 2;
        T midItem = items[mid];
        int compareVal = midItem.compareTo(toFind);
        if (compareVal < 0) {
          low = mid + 1;
        } else {
          if (mid == 0 || items[mid - 1].compareTo(toFind) < 0) {
            return mid;
          }
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  /**
   * 查找最后一个小于给定元素值的元素
   *
   * @param items 待查找元素列表
   * @param toFind 待查找元素
   * @param <T> 继承Comparable接口的实体
   * @return 最后一个小于给定元素值的元素的下标
   */
  public static <T extends Comparable<T>> int findLELast(T[] items, T toFind) {
    if (items != null && items.length > 0 && toFind != null) {
      int low = 0;
      int high = items.length - 1;
      for (; low <= high; ) {
        int mid = low + (high - low) / 2;
        T midItem = items[mid];
        int compareVal = midItem.compareTo(toFind);
        if (compareVal <= 0) {
          if (mid == items.length - 1 || items[mid + 1].compareTo(toFind) > 0) {
            return mid;
          }
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

//  /**
//   * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
//   * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 
//   * 来源：力扣（LeetCode）
//   * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
//   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//   * @param items 进行了旋转操作的数组
//   * @param toFind 待查找元素
//   * @param <T> 继承Comparable接口的实体
//   * @return 等于给定元素值的元素的下标
//   */
//  public static <T extends Comparable<T>> int findRotatedSorted(T[] items, T toFind){
//    if (items != null && items.length > 0 && toFind != null) {
//      int low = 0;
//      int high = items.length-1;
//      for (; low <= high; ) {
//        int mid = low + (high - low) / 2;
//        T midItem = items[mid];
//        int compareVal = midItem.compareTo(toFind);
//      }
//    }
//  }

  public static void main(String[] args) {
    Integer[] integers = new Integer[]{1, 3, 5, 7, 8, 8, 8, 8, 9};
    System.out.println(find(integers, 8));
    System.out.println(findFirst(integers, 8));
    System.out.println(findLast(integers, 8));
    System.out.println(findGEFirst(integers, 6));
    System.out.println(findLELast(integers, 6));
  }
}
