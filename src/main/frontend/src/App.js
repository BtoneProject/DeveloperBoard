// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
    const [hello, setHello] = useState('')

    /*useEffect(() => {
        axios.get('/api/hello')
            .then(response => setHello(response.data))
            .catch(error => console.log(error))
    }, []);*/
    const onClickEnter = () => {
        axios.get('/api/hello')
            .then(res => {
                if (res.data !== undefined) {
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