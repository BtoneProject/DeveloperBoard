// src/main/frontend/src/App.js

import './css/App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import {MemoryRouter, Route, Link, Routes} from 'react-router-dom';
import CreateArticle from "./CreateArticle";
import DetailArticle from "./DetailArticle";
import Home from "./Home";

function App() {
    return (
        <MemoryRouter>
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
                </Routes>
            </div>
        </MemoryRouter>
    );
}

export default App;