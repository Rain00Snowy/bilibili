package com.video.util;

import com.video.entity.dto.ResultDTO;

/**
 * ResultUtil 工具类可以快速地获取一个ResultDTO。
 * ResultDTO是一种标准化的响应格式，用于表示操作是否成功，相关消息和返回的数据。
 */
public class ResultUtil {

    // 创建并返回一个表示成功的ResultDTO，只包含消息。
    public static ResultDTO successResult(String msg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(true); // 设置操作结果为成功。
        resultDTO.setMessage(msg); // 设置成功消息。
        return resultDTO;
    }

    // 创建并返回一个表示失败的ResultDTO，只包含消息。
    public static ResultDTO failResult(String msg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(false); // 设置操作结果为失败。
        resultDTO.setMessage(msg); // 设置失败消息。
        return resultDTO;
    }

    // 创建并返回一个表示成功的ResultDTO，包含数据和默认成功消息。
    public static <T>ResultDTO<T> successResult(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(true); // 设置操作结果为成功。
        resultDTO.setMessage("请求成功"); // 设置默认成功消息。
        resultDTO.setData(t); // 设置返回的数据。
        return resultDTO;
    }

    // 创建并返回一个表示失败的ResultDTO，包含数据和默认失败消息。
    public static <T>ResultDTO<T> failResult(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(false); // 设置操作结果为失败。
        resultDTO.setMessage("请求失败"); // 设置默认失败消息。
        resultDTO.setData(t); // 设置返回的数据。
        return resultDTO;
    }

    // 创建并返回一个表示成功的ResultDTO，包含数据和自定义消息。
    public static <T>ResultDTO<T> successResult(T t, String msg) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(true); // 设置操作结果为成功。
        resultDTO.setMessage(msg); // 设置自定义成功消息。
        resultDTO.setData(t); // 设置返回的数据。
        return resultDTO;
    }

    // 创建并返回一个表示失败的ResultDTO，包含数据和自定义消息。
    public static <T>ResultDTO<T> failResult(T t, String msg) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(false); // 设置操作结果为失败。
        resultDTO.setMessage(msg); // 设置自定义失败消息。
        resultDTO.setData(t); // 设置返回的数据。
        return resultDTO;
    }
}
