import React, {useEffect, useState} from "react";
import axios from "axios";


function Home() {
    const [board, setBoard] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('/board/boardList');
            setBoard(response.data);
        } catch (error) {
            console.error('Error fetching data : ' + error);
        }
    }
    return (

        <div className="row row-cols-1 row-cols-md-4 g-4 all-card">
            {board.map((item) => (
                <div key={item.postNo} className="col">
                    <div className="card" style={{height:'450px'}}>
                        <img src="https://velog.velcdn.com/images/designc/post/edcb6480-d8b8-4010-b70e-8ce91538a3a9/image.gif" className="card-img-top" alt="..."/>
                        <div className="card-body">
                            <h5 className="card-title">{item.title}</h5>
                            <p className="card-text">{item.content}</p>
                        </div>
                        <div className="card-footer">
                            <small className="text-muted">Last updated 3 mins ago</small>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default Home;