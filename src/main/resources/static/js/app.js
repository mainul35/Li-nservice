const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');
import '../styles/main.scss';

import {FrontEndHeader} from './components/landing/header'

class App extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<FrontEndHeader/>
		);
	}
}

ReactDOM.render(
	<div>
		<App />
	</div>
	,
	document.getElementById('react')
);
