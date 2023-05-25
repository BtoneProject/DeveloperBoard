import React, {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import axios from "axios";

const DetailArticle = ({children}) => {
    const location = useLocation();
    const postno = location.state ? location.state.postNo : null;
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    if (!postno) {
        return <div>No data available</div>;
    }
    const fetchData = async () => {
        try {
            const response = await axios.post('/board/boardDetail', {postNo : postno});
            setData(response.data);
        } catch (error) {
            console.error('Error fetching data : ' + error);
        }
    }

    return (
        <div>
            <h1>상세페이지</h1>
            <h2>게시번호 : {data.postNo}</h2>
            <h2>제목 : {data.title}</h2>
            <h4>내용 : {data.content}</h4>
        </div>
    );
}

const ErrorBoundary = ({children}) => {
    try {
        return children;
    } catch (error) {
        return <div>오류: {error.message}</div>;
    }
};

export default () => (
    <ErrorBoundary>
        <DetailArticle />
    </ErrorBoundary>
);