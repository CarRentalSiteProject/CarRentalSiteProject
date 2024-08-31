import React from 'react';
import Search from './Search';
import Intro from './Intro';
import TopCars from './TopCars';
import Fleets from './Fleets';
import Comments from './Comments';
import Mobile from './Mobile';
import Footer from './Footer';

function MainContent() {
    return (
        <div>
            <main>
                <Search />
                <Intro />
                <TopCars />
                <Fleets />
                <Comments />
                <Mobile />
                <Footer />
            </main>
        </div>
    );
}

export default MainContent;