import React from 'react';
import { useLocation } from 'react-router-dom';

export default function DetailArticle() {
    const location = useLocation();
    const item = location.state ? location.state.item : null;

    if (!item) {
        return <div>No data available</div>;
    }
    return (
        <div>
            <h1>상세페이지</h1>
            <h2>게시번호 : {item.postNo}</h2>
            <h2>제목 : {item.title}</h2>
            <h4>내용 : {item.content}</h4>
        </div>
    );
}