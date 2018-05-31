import React from 'react';
import ReactDOM from 'react-dom';

import { Layout, Menu, Breadcrumb, Icon } from 'antd';
const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

import 'antd/dist/antd.css';
import '../../../styles/base/_base.scss';

class FrontEndHeader extends React.Component {
	constructor(props) {
		super(props);
		 this.onSelectMenuItem = this.onSelectMenuItem.bind(this);
	}

	onSelectMenuItem(e){
		document.querySelector(".ant-menu-item-selected").classList.remove("ant-menu-item-selected");
		e.target.classList.add("ant-menu-item-selected");
	}

	render() {
		return (
			<Layout>
				<Header className="header">
					<div className="logo" />

					<div style={{ float: 'right' }}>
						<ul
							className="ant-menu ant-menu-dark ant-menu-root ant-menu-horizontal"
							role="menu"
							style={{"lineHeight": "64px"}}
						>
							<li onClick={this.onSelectMenuItem} className="ant-menu-item right ant-menu-item-selected">
								Log in
							</li>
							<li onClick={this.onSelectMenuItem} className="ant-menu-item right">
								Sign Up
							</li>
						</ul>
					</div>
				</Header>
			</Layout>
		);
	}
}

export { FrontEndHeader };
