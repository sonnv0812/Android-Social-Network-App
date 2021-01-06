package com.example.facebookapp.data.base;

/**
 * Interface định nghĩa listener lắng nghe sự kiện của tầng Data
 *
 * @param <T> là kiểu dữ liệu trả về
 */
public interface OnDataLoadedListener<T> {
    /**
     * Phương thức khi sự kiện xảy ra
     *
     * @param data là dữ liệu trả về
     */
    void onSuccess(T data);

    /**
     * Phương thức khi sự kiện bị lỗi
     *
     * @param exception là lỗi xảy ra
     */
    void onFailure(Exception exception);
}
