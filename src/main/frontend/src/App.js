// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
    const [hello, setHello] = useState('')
    const onClickEnter = () => {
        axios.get('/board/boardList')
            .then(res => {
                if (res.data !== undefined) {
                    console.log(res.data);
                    setHello(res.data);
                }
            })
            .catch((e) => {
                console.log(e);
            })
    };

    return (
        <div>
            <button onClick={onClickEnter}>입장</button>
            <h1>{hello}</h1>
        </div>
    );
}

export default App;