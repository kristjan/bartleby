require 'rexml/document'
require 'rho/rhocontroller'
require 'helpers/browser_helper'

class StationController < Rho::RhoController
  include BartHelper
  include BrowserHelper

  def index
    app_info "Stations#index"
    @stations = Station.find(:all)
    app_info "Found #{@stations.size} stations"

    if @stations.empty?
      url = bart_url_for(:stn, :stns)
      app_info "Loading from #{url}"
      Rho::AsyncHttp.get(
        :url => url,
        :callback => url_for(:action => :fetch_stations_callback)
      )

      render :action => :wait
    else
      app_info "Rendering the index"
      render
    end
  end

  def show
    @station = Station.find(@params['id'])
    if @station
      render :action => :show
    else
      redirect :action => :index
    end
  end

  def fetch_stations_callback
    app_info "Building stations"
    xml = REXML::Document.new(@params['body'])
    Station.create_from_xml(xml)
    app_info "Done building stations"
    WebView.navigate url_for(:action => :index)
  end
end
