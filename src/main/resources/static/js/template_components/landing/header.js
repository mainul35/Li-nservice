import React from 'react';
import ReactDOM from 'react-dom';
import Button from '@material-ui/core/Button';

export class FrontEndHeader extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div>
				<Button variant="raised" color="primary">
					Hello World
				</Button>
			</div>
		);
	}
}
