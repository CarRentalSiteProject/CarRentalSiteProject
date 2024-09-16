import React from 'react';
import SearchEmbeded from './SearchEmbeded';
import Intro from './Intro';
import TopCars from './TopCars';
import Fleets from './Fleets';
import Comments from './Comments';
import Mobile from './Mobile';

function MainContent() {
  return (
    <main>
      <SearchEmbeded />
      <Intro />
      <TopCars />
      <Fleets />
      <Comments />
    </main>
  );
}

export default MainContent;