import React, {useState} from 'react';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

export default function CreateArticle() {
    const navigate = useNavigate();

    // 데이터 바인딩 용 formData 설정
    const [formData, setFormData] = useState({
        title: '',
        content: '',
        idno:14,
    });
    
    // 값이 변할때마다 formData에 해당 값들을 넣어줌
    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    }
    
    // 등록 버튼 클릭
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('/board/create', formData);
            navigate("/");
            console.log(response);
        } catch (error) {
            console.error('Error fetching data : ' + error);
        }
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label htmlFor="title">Title:</label>
                <input
                    type="text"
                    id="title"
                    name="title"
                    onChange={handleInputChange}
                />
                <label htmlFor="content">Content:</label>
                <textarea
                    id="content"
                    name="content"
                    onChange={handleInputChange}
                ></textarea>

                <button type="submit" className="btn btn-success">등록</button>
                <button type="button" className="btn btn-dark">취소</button>
            </form>
        </div>
    );
}