import React, { useState, useEffect } from "react";
import "./App.css";
import SearchBar from "./components/toolbar/SearchBar";
import { api } from "./util/api";
import CraftspersonRow from "./components/list/CraftspersonRow";
import { sortByNumberOfMentees, sortByCraftspeopleWithoutMentor } from "./util/sorting";
import { filter } from "./util/filtering";
import ButtonToolbar from 'react-bootstrap/ButtonToolbar'
import ToggleButton from 'react-bootstrap/ToggleButton';
import ToggleButtonGroup from 'react-bootstrap/ToggleButtonGroup';

function App() {
    const defaultSort = sortByNumberOfMentees
    
    const [craftspeople, setCraftsPeople] = useState([]);
    const [filteredCrafspeople, setFilteredCrafspeople] = useState(craftspeople);
    const [sortAlgorithm, setSortAlgorithm] = useState(() => defaultSort);
    const [backendFetchError, setBackendFetchError] = useState(null);

    function filterCraftspeople(searchedValue) {
        const filteredCraftspeople = filter(craftspeople, searchedValue);
        craftspeople.sort(sortAlgorithm);
        setFilteredCrafspeople(filteredCraftspeople);
    };

    function makeSortOnClickListener (sortAlgorithmToUse) {
        return () => {
            setSortAlgorithm(() => sortAlgorithmToUse);
            // here we don't use the current algorithm because it's outdated 
            filteredCrafspeople.sort(sortAlgorithmToUse);
            setFilteredCrafspeople(filteredCrafspeople);
        }
    };

    useEffect(() => {
        api("craftspeople")
            .then(fetchedCraftspeople => {
                fetchedCraftspeople.sort(defaultSort)
                setCraftsPeople(fetchedCraftspeople);
                setFilteredCrafspeople(fetchedCraftspeople);
            })
            .catch(error => {
                console.log(error);
                setBackendFetchError(error)
            });
    }, [defaultSort]);

    return (
        <div className='App'>
            <div>
                <h1>Mementor</h1>
                {backendFetchError &&   
                    <div class="alert alert-danger" role="alert">
                        <strong>Oh snap!</strong> Looks like there was an error while fetching the data.
                    </div>}
                <div className="container">
                    <SearchBar onEnter={filterCraftspeople} />
                    <ButtonToolbar>
                        <ToggleButtonGroup type="radio" name="options" defaultValue={1}>
                            <ToggleButton variant="light" onClick={makeSortOnClickListener(sortByNumberOfMentees)} prechecked value={1}>Sort by number of mentees</ToggleButton>
                            <ToggleButton variant="light" onClick={makeSortOnClickListener(sortByCraftspeopleWithoutMentor)} value={2}>Sort by mentor</ToggleButton>
                        </ToggleButtonGroup>
                    </ButtonToolbar>
                </div>
                {filteredCrafspeople.map(craftsperson => 
                    <CraftspersonRow key={craftsperson.id} craftsperson={craftsperson} />)}
            </div>
        </div>
    );
}

export default App;
