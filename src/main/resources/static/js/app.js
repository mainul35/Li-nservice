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
			<h1>Hello From React</h1>
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
