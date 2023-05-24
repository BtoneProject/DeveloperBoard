// src/main/frontend/src/App.js

import './css/App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import {MemoryRouter, Route, Link, Routes} from 'react-router-dom';
import MyInfo from './components/UserInfo';
import CreateArticle from "./CreateArticle";
import DetailArticle from "./DetailArticle";
import Home from "./Home";
import Header from "./components/Header";

function App() {
    return (
        <MemoryRouter>
            <Header></Header>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">홈</Link>
                        </li>
                        <li>
                            <Link to="/article">게시판</Link>
                        </li>
                    </ul>
                </nav>

                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/article" element={<CreateArticle/>}/>
                    <Route path="/detail" element={<DetailArticle/>}/>
                    <Route path="/userInfo" element={<MyInfo/>}></Route>
                </Routes>
            </div>
        </MemoryRouter>
    );
}

export default App;