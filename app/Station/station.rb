require 'helpers/bart_helper'

class Station
  include Rhom::PropertyBag
  include BartHelper

  PROPERTIES = [
    :name,
    :abbr,
    :address,
    :city,
    :county,
    :state,
    :zipcode,
  ]

  def self.create_from_xml(xml)
    puts "Building all stations"
    xml.elements.each("//station") do |station|
      s = Station.create_one_from_xml(station)
      puts s.inspect
    end
  end

  def self.create_one_from_xml(station)
    puts "Building station: #{station.to_s}"
    params = {}
    station.elements.each('*') do |attr|
      puts "\t#{attr.name}: #{attr.text}"
      params[attr.name.to_sym] = attr.text
    end
    create(params)
  end
end
